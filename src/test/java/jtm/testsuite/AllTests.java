package jtm.testsuite;

import jtm.activity01.ConfigTests;
import jtm.activity02.ConfigAndHelloTest;
import jtm.activity03.ArrayTest;
import jtm.activity03.BlackKnightTest;
import jtm.activity04.TrafficManagementTest;
import jtm.activity05.EncapsulationTests;
import jtm.activity06.InterfaceTests;
import jtm.activity07.SimpleCalcTests;
import jtm.activity08.OrdersTests;
import jtm.activity09.StreamEditorTest;
import jtm.activity10.TttCliTest;
import jtm.activity10.TttNetTest;
import jtm.activity11.ArrayFillerManagerTest;
import jtm.activity12.GenericsTest;
import jtm.activity12.HolidayTest;
import jtm.activity12.RegExTest;
import jtm.activity13.BoardTest;
import jtm.activity13.CrocodileTest;
import jtm.activity13.GameTest;
import jtm.activity14.XMLCarsTest;
import jtm.activity15.JsonCarsTest;
import jtm.activity16.DatabaseTest;
import jtm.activity17.DatabaseUnitTest;
import jtm.activity18.WebTests;
import jtm.activity19.ColorSliderTests;
import jtm.activity20.PersonMatcherTests;
import jtm.activity21.GitMergeTest;
import jtm.extra01.GetOneTest;
import jtm.extra01.ZodiacTest;
import jtm.extra02.ArrayListMethodsTest;
import jtm.extra02.LetsRideTest;
import jtm.extra03.PracticalNumbersTest;
import jtm.extra04.AnimalTests;
import jtm.extra05.LogTest;
import jtm.extra06.PersonManagerTest;
import jtm.extra07.ChatServerTest;
import jtm.extra08.ChatClientTest;
import jtm.extra09.JNIClassTest;
import jtm.extra20.CodeWeightTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Run all tests cases of the project and calculate total result
 */
@RunWith(JtmSuite.class)
@SuiteClasses({
        ConfigTests.class, // 1
        ConfigAndHelloTest.class, // 2
        ArrayTest.class, // 3
        BlackKnightTest.class, // 3
        TrafficManagementTest.class, // 4
        EncapsulationTests.class, // 5
        InterfaceTests.class, // 6
        SimpleCalcTests.class, // 7
        OrdersTests.class, // 8
        StreamEditorTest.class, // 9
        TttNetTest.class, // 10
        ArrayFillerManagerTest.class, // 11
        GenericsTest.class, // 12
        HolidayTest.class, // 12
        RegExTest.class, // 12
        CrocodileTest.class, // 13
        GameTest.class, // 13
        XMLCarsTest.class, // 14
        JsonCarsTest.class, // 15
        DatabaseTest.class, // 16
        DatabaseUnitTest.class, // 17
        WebTests.class, // 18
        ColorSliderTests.class, // 19
        PersonMatcherTests.class, // 20
        GitMergeTest.class, // 21
        // extra activities
        // 22 — external project — Android application
        GetOneTest.class, // e01
        ZodiacTest.class, // e01
        ArrayListMethodsTest.class, // e02
        LetsRideTest.class, // e02
        PracticalNumbersTest.class, // e03
        AnimalTests.class, // e04
        LogTest.class, // e05
        PersonManagerTest.class, // e06
        ChatServerTest.class, // e07
        ChatClientTest.class, // e08
        JNIClassTest.class, // e09
        CodeWeightTest.class // e10

})
public class AllTests {

}
