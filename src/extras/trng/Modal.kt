package extras.trng

class InvalidResponseException : Exception {
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    constructor(message: String?) : super(message) {}
}

class InvalidMethodCallException : RuntimeException {
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    constructor(message: String?) : super(message) {}
}

enum class NumberBases(val base: Int) {
    Binary(2), Decimal(10), Hexadecimal(16), Octal(8);
}