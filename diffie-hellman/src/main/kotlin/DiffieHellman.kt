import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.asJavaRandom

object DiffieHellman {

    fun privateKey(prime: BigInteger): BigInteger {
        var key: BigInteger
        do {
            key = BigInteger(prime.bitLength(), Random.asJavaRandom())
        } while (key >= prime)
        return key
    }

    fun publicKey(prime: BigInteger, g: BigInteger, privateKey: BigInteger): BigInteger = g.modPow(privateKey, prime)

    fun secret(prime: BigInteger, publicKey: BigInteger, privateKey: BigInteger): BigInteger =
            publicKey.modPow(privateKey, prime)
}
