package jtm.activity19;

import jtm.testsuite.JTMTest;
import org.apache.log4j.Logger;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import static jtm.TestUtils.timeout;

/**
 * This test is open sourced intentionally. You can use it as a template for
 * automated GUI test in your teamwork project. It uses AssertJ framework,
 * particularly AssertJ Swing to test Swing application. Look at
 * http://joel-costigliola.github.io/assertj/ for more info how to use it.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Call test methods in
// alphabetical order

//If you use this class as a template, simply remove 'extends JTMTest' part and replace references to
//timeout variables to your own values
public class ColorSliderGUITest extends JTMTest {

    private static FrameFixture fixture;

    private static final Logger logger = Logger.getLogger(ColorSliderGUITest.class);

    @Rule
    public Timeout globalTimeout = Timeout.seconds(timeout);

    /**
     * Prepare application for testing
     */
    @BeforeClass
    public static void onSetUp() {
        System.setProperty("java.awt.headless", "false");
        ColorSlider app = GuiActionRunner.execute(ColorSlider::new);
        /*
         * If test Application extends JFrame, you can assign reference from it to frame
         * fixture directly. Otherwise you have to provide access to used JFrame object
         * of the application.
         */
        fixture = new FrameFixture(app.frame);
        // Show window of the application
        fixture.show();
        // This is just workaround to fix size of the application window:
        app.frame.setBounds(100, 100, 450, 300);
    }

    /**
     * Check that initial color of text box is black
     */
    @Test
    public void test01InitialColor() {
        fixture.textBox("testArea").background().requireEqualTo("000000");
        logger.info("Initial Color OK");
    }

    /**
     * Check that color of text box background is changed accordingly to color
     * sliders
     */
    @Test
    public void test02ColorSliders() {
        fixture.slider("redSlider").slideToMaximum();
        fixture.textBox("testArea").background().requireEqualTo("FF0000");
        fixture.slider("greenSlider").slideToMaximum();
        fixture.textBox("testArea").background().requireEqualTo("FFFF00");
        fixture.slider("blueSlider").slideToMaximum();
        fixture.textBox("testArea").background().requireEqualTo("FFFFFF");
        fixture.slider("redSlider").slideToMinimum();
        fixture.textBox("testArea").background().requireEqualTo("00FFFF");
        fixture.slider("greenSlider").slideToMinimum();
        fixture.textBox("testArea").background().requireEqualTo("0000FF");
        fixture.slider("blueSlider").slideToMinimum();
        fixture.textBox("testArea").background().requireEqualTo("000000");
        logger.info("Color Sliders OK");
    }
}
