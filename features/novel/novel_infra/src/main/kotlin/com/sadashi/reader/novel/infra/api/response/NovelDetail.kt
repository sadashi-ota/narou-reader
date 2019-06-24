package com.sadashi.reader.novel.infra.api.response

import pl.droidsonroids.jspoon.annotation.Selector

class NovelDetail {
    @Selector(".contents1 > a") var title: String? = null
    @Selector(".novel_subtitle") var subtitle: String? = null
    @Selector(".novel_view > p") var body: List<String>? = null
}