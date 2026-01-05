package com.aurea.batikcam

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class ImageClassificationHelper(private val context: Context) {
    private lateinit var tflite: Interpreter
    private lateinit var labels: List<String>

    companion object {
        private const val MODEL_PATH = "batikcamv5.tflite"
        private const val LABELS_PATH = "labels_v1.txt"
        private var outputSize: Int = 1001
        const val MODEL_INPUT_WIDTH = 224
        const val MODEL_INPUT_HEIGHT = 224
    }

    init {
        initializeInterpreter()
        loadLabels()
    }

    private fun initializeInterpreter() {
        try {
            val model = loadModelFile()
            val options = Interpreter.Options()
            tflite = Interpreter(model, options)
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Error initializing TensorFlow Lite interpreter: ${e.message}")
        }
    }

    private fun loadLabels() {
        try {
            labels = context.assets.open(LABELS_PATH).bufferedReader().useLines { lines ->
                lines.map { line ->
                    line.substringAfter(" ")
                }.toList()
            }
            outputSize = labels.size
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Error loading labels: ${e.message}")
        }
    }

    private fun loadModelFile(): MappedByteBuffer {
        val assetFileDescriptor = context.assets.openFd(MODEL_PATH)
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    fun classifyImage(inputImage: Bitmap): List<Pair<String, Float>> {
        val inputTensor = preprocessImage(inputImage)

        val outputBuffer = Array(1) { FloatArray(outputSize) }

        tflite.run(inputTensor, outputBuffer)

        return parseOutput(outputBuffer[0])
    }

    private fun preprocessImage(image: Bitmap): ByteBuffer {

        val inputImage = Bitmap.createScaledBitmap(image, 224, 224, true)

        val byteBuffer = ByteBuffer.allocateDirect(4 * 3 * 224 * 224)
        byteBuffer.order(ByteOrder.nativeOrder())

        val normalizedImage = Array(3) { Array(224) { FloatArray(224) } }

        for (y in 0 until 224) {
            for (x in 0 until 224) {
                val pixel = inputImage.getPixel(x, y)

                val r = (pixel shr 16 and 0xFF) / 255.0f
                val g = (pixel shr 8 and 0xFF) / 255.0f
                val b = (pixel and 0xFF) / 255.0f

                normalizedImage[0][y][x] = r
                normalizedImage[1][y][x] = g
                normalizedImage[2][y][x] = b
            }
        }

        for (c in 0 until 3) {
            for (y in 0 until 224) {
                for (x in 0 until 224) {
                    byteBuffer.putFloat(normalizedImage[c][y][x])
                }
            }
        }

        return byteBuffer
    }

    private fun applySoftmax(output: FloatArray): FloatArray {
        val expValues = output.map { Math.exp(it.toDouble()).toFloat() }
        val sumExpValues = expValues.sum()
        return expValues.map { it / sumExpValues }.toFloatArray()
    }

    private fun parseOutput(output: FloatArray): List<Pair<String, Float>> {
        val probabilities = applySoftmax(output)

        return labels.zip(probabilities.toList())
            .sortedByDescending { it.second }
            .take(3)
    }
}
