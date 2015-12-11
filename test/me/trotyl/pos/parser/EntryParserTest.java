package me.trotyl.pos.parser;

import me.trotyl.pos.unknown.Entry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class EntryParserTest {

    private EntryParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new EntryParser();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void parse_should_have_proper_result() {

        List<String> inputs = asList("ITEM000001-3", "ITEM000003-2", "ITEM000005-2");

        List<Entry> items = parser.parse(inputs);

        assertThat(items.size(), is(3));
        assertThat(items.get(0).getBarcode(), is("ITEM000001"));
        assertThat(items.get(0).getAmount(), is(3));
        assertThat(items.get(1).getBarcode(), is("ITEM000003"));
        assertThat(items.get(1).getAmount(), is(2));
        assertThat(items.get(2).getBarcode(), is("ITEM000005"));
        assertThat(items.get(2).getAmount(), is(2));
    }
}
