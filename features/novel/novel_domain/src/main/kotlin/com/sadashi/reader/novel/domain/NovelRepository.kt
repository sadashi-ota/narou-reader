import com.sadashi.reader.novel.domain.NCode
import com.sadashi.reader.novel.domain.dto.NovelDetail
import com.sadashi.reader.novel.domain.dto.NovelSummary
import io.reactivex.Single

interface NovelRepository {
    fun searchNovel(): Single<List<NovelSummary>>
    fun getNovelDetail(ncode: NCode, page: Int): Single<NovelDetail>
}