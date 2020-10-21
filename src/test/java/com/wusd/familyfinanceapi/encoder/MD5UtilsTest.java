package com.wusd.familyfinanceapi.encoder;

import com.wusd.familyfinanceapi.FamilyFinanceApiApplicationTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MD5UtilsTest extends FamilyFinanceApiApplicationTests {

    @Test
    void md5() {
        String wusdMd5 = MD5Utils.md5("wusd");
        assertEquals("A1AC9FBBFD93A2B877DF31CDAB2C8193", wusdMd5);
    }
}