import java.io.FileInputStream
import java.util.Properties

object Prop {
    val map = mutableMapOf<String, String>()

    fun loadProperties(filepath: String) {
        val properties = Properties()
        val inputStream = FileInputStream(filepath)
        properties.load(inputStream)

        properties.forEach {
            map[it.key as String] = "${it.value}"
        }
    }
}