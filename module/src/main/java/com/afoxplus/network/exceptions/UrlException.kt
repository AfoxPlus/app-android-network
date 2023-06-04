package com.afoxplus.network.exceptions

class UrlException(errorMessage: String) : Exception("$URL_EXCEPTION_MESSAGE:[$errorMessage]")

private const val URL_EXCEPTION_MESSAGE = "You forgot to add the ServiceClient annotation"