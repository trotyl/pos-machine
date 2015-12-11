package me.trotyl.pos.parser;

import me.trotyl.pos.model.Discount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class DiscountParserTest {

    private DiscountParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new DiscountParser();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void parse_should_have_proper_result() {

        List<String> inputs = asList("ITEM000001:75", "ITEM000005:90");

        List<Discount> discounts = parser.parse(inputs);

        assertThat(discounts.size(), is(2));
        assertThat(discounts.get(0).getBarcode(), is("ITEM000001"));
        assertThat(discounts.get(0).getDiscount(), is(75.0));
        assertThat(discounts.get(1).getBarcode(), is("ITEM000005"));
        assertThat(discounts.get(1).getDiscount(), is(90.0));
    }

    @Test
    public void parse_should_have_proper_result_with_floating_price() {

        List<String> inputs = asList("ITEM000001:75.125", "ITEM000005:90.625");

        List<Discount> discounts = parser.parse(inputs);

        assertThat(discounts.size(), is(2));
        assertThat(discounts.get(0).getBarcode(), is("ITEM000001"));
        assertThat(discounts.get(0).getDiscount(), is(75.125));
        assertThat(discounts.get(1).getBarcode(), is("ITEM000005"));
        assertThat(discounts.get(1).getDiscount(), is(90.625));
    }

    @Test
    public void parse_should_have_proper_result_with_invalid_inputs() {

        List<String> inputs = asList("ITEM000001:75", "90:ITEM000005");

        List<Discount> discounts = parser.parse(inputs);

        assertThat(discounts.size(), is(1));
        assertThat(discounts.get(0).getBarcode(), is("ITEM000001"));
        assertThat(discounts.get(0).getDiscount(), is(75.0));
    }

    @Test
    public void parse_should_have_proper_result_with_empty_inputs() {

        List<String> inputs = Collections.emptyList();

        List<Discount> discounts = parser.parse(inputs);

        assertThat(discounts.size(), is(0));
    }
}
