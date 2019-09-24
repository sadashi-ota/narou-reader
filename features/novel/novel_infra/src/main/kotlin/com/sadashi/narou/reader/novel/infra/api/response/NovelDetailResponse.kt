package com.sadashi.narou.reader.novel.infra.api.response

import pl.droidsonroids.jspoon.annotation.Selector

class NovelDetailResponse {
    @Selector(value = ".contents1 > a", attr = "href") var ncode: String? = null
    @Selector(".contents1 > a") var title: String? = null
    @Selector(".novel_subtitle") var subtitle: String? = null
    @Selector("#novel_no") var novelNo: String? = null
    @Selector(".novel_view > p") var body: List<String>? = null
}