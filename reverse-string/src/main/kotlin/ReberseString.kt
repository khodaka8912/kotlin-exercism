fun reverse(str: String): String = when (str) {
    "" -> ""
    else -> str[str.length - 1] + reverse(str.substring(0 until str.length - 1))
}