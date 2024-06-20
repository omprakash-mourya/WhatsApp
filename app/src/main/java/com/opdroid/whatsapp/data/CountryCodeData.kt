package com.opdroid.whatsapp.data

data class CountryCode(val countryCode: String, val phoneCode: String)

val countryCodes = listOf(
    CountryCode("IN", "+91"),
    CountryCode("US", "+1"),
    CountryCode("GB", "+44"),
    CountryCode("CA", "+1"),
    CountryCode("AU", "+61"),
    CountryCode("DE", "+49"),
    CountryCode("FR", "+33"),
    CountryCode("IT", "+39"),
    CountryCode("JP", "+81"),
    CountryCode("KR", "+82"),
    CountryCode("MY", "+60"),
    CountryCode("RU", "+7"),
    CountryCode("CN", "+86"),
    CountryCode("BR", "+55"),
    CountryCode("MX", "+52"),
    CountryCode("ZA", "+27"),
    CountryCode("NG", "+234"),
    CountryCode("EG", "+20"),
    CountryCode("SA", "+966"),
    CountryCode("AE", "+971"),
    CountryCode("KW", "+965"),
    CountryCode("QA", "+974"),
    CountryCode("OM", "+968"),
    CountryCode("BH", "+973"),
    CountryCode("JO", "+962"),
    CountryCode("LB", "+961"),
    CountryCode("SY", "+963"),
    CountryCode("YE", "+967"),
    CountryCode("IQ", "+964"),
    CountryCode("IL", "+972"),
    CountryCode("PS", "+970"),
    CountryCode("PK", "+92"),
    CountryCode("AF", "+93"),
    CountryCode("BD", "+880"),
    CountryCode("BT", "+975"),
    CountryCode("IN", "+91"),
    CountryCode("LK", "+94"),
    CountryCode("MV", "+960"),
    CountryCode("NP", "+977"),
    CountryCode("IR", "+98"),
    CountryCode("TR", "+90"),
    CountryCode("AZ", "+994"),
    CountryCode("GE", "+995"),
    CountryCode("AM", "+374"),
    CountryCode("CY", "+357"),
    CountryCode("GR", "+30"),
    CountryCode("HU", "+36"),
    CountryCode("IS", "+354"),
    CountryCode("IE", "+353"),
    CountryCode("LV", "+371")
    // Add more country codes here...
)

fun getPhoneNumberCode(countryCode: String): String? {
    return countryCodes.find { it.countryCode == countryCode }?.phoneCode
}