package com.aurea.batikcam.data.data

import com.aurea.batikcam.data.model.Gallery
import java.util.UUID


object GalleryData {
    val galleryList = listOf(
        Gallery(idGallery = UUID.randomUUID().toString(),title = "Batik Kawung", image = "batik-kawung-bantal.jpeg", idBatik = "batik-kawung"),
        Gallery(idGallery = UUID.randomUUID().toString(),title = "Batik Solo", image = "batiksolo.jpeg", idBatik = "batik-sogan"),
        Gallery(idGallery = UUID.randomUUID().toString(),title = "Batik Parang", image = "batikparang.jpg", idBatik = "batik-parang"),
        Gallery(idGallery = UUID.randomUUID().toString(),title = "Batik Mega Mendung", image = "motif-batik-mega-mendung-khas-cirebon.jpg", idBatik = "batik-megamendung")
    )
}