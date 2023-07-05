package com.example.a20230703_urvishpatel_nycschools
import org.junit.Assert.*
import org.junit.Test

class StringUtilsExmKtTest{

    @Test
    fun `testing for removing the duplicates string from value`(){
        val str = "apple"
        val expectedResult = "aple"


        val finalresult = removeDuplicates(str)
        assertEquals(expectedResult,finalresult)
    }
}