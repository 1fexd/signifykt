package fe.signify

import com.google.crypto.tink.subtle.Ed25519Verify
import java.util.*
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class Signature private constructor(val algorithm: ByteArray, val keyNumber: ByteArray, val signature: ByteArray) {
    companion object {
        private const val KEY_ALGORITHM_LEN: Int = 2
        private const val KEY_NUMBER_LEN: Int = 8

        private const val DECODED_PUBLIC_KEY_LEN: Int =
            KEY_ALGORITHM_LEN + KEY_NUMBER_LEN + Ed25519Verify.PUBLIC_KEY_LEN
        private val SUPPORTED_ALGORITHM = "Ed".toByteArray()

        @OptIn(ExperimentalEncodingApi::class)
        fun fromString(base64: String): Signature {
            val decoded = Base64.decode(base64)
            if (decoded.size != DECODED_PUBLIC_KEY_LEN) throw IllegalArgumentException("Decoded key length must be $DECODED_PUBLIC_KEY_LEN (was ${decoded.size})")

            val algorithm = Arrays.copyOfRange(decoded, 0, KEY_ALGORITHM_LEN)
            if (!algorithm.contentEquals(SUPPORTED_ALGORITHM)) {
                throw IllegalArgumentException("Algorithm is not supported!")
            }

            val keyNumber = Arrays.copyOfRange(decoded, KEY_ALGORITHM_LEN, 2 + KEY_NUMBER_LEN)
            val signature = Arrays.copyOfRange(decoded, 10, 10 + Ed25519Verify.PUBLIC_KEY_LEN)

            return Signature(algorithm, keyNumber, signature)
        }
    }
}
