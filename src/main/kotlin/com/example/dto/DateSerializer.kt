import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import java.time.LocalDate
import java.time.ZoneId
import java.time.Instant

@Serializable
object DateSerializer : KSerializer<LocalDate> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDateFromTimestamp", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: LocalDate) {
        val timestamp = value.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
        encoder.encodeLong(timestamp)
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        val timestamp = decoder.decodeLong()
        return Instant.ofEpochSecond(timestamp).atZone(ZoneId.systemDefault()).toLocalDate()
    }
}
