package me.trotyl.pos.lexer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;



public class LexerTest {

    private Lexer lexer;

    @Before
    public void setUp() throws Exception {
        lexer = new Lexer();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void parse_should_have_proper_result() {

        String inputString = format("ITEM000001:40%nITEM000003:50%nITEM000005:60%n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());

        List<String> results = lexer.parse(inputStream);

        assertThat(results.size(), is(3));
        assertThat(results.get(0), is("ITEM000001:40"));
        assertThat(results.get(1), is("ITEM000003:50"));
        assertThat(results.get(2), is("ITEM000005:60"));
    }

    @Test
    public void parse_should_have_proper_result_without_last_LF() {

        String inputString = format("ITEM000001:40%nITEM000003:50%nITEM000005:60");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());

        List<String> results = lexer.parse(inputStream);

        assertThat(results.size(), is(3));
        assertThat(results.get(0), is("ITEM000001:40"));
        assertThat(results.get(1), is("ITEM000003:50"));
        assertThat(results.get(2), is("ITEM000005:60"));
    }

    @Test
    public void parse_should_have_proper_result_with_tailing_LFs() {

        String inputString = format("ITEM000001:40%nITEM000003:50%nITEM000005:60%n%n%n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());

        List<String> results = lexer.parse(inputStream);

        assertThat(results.size(), is(3));
        assertThat(results.get(0), is("ITEM000001:40"));
        assertThat(results.get(1), is("ITEM000003:50"));
        assertThat(results.get(2), is("ITEM000005:60"));
    }

    @Test
    public void parse_should_have_proper_result_with_leading_LFs() {

        String inputString = format("%n%n%nITEM000001:40%nITEM000003:50%nITEM000005:60%n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());

        List<String> results = lexer.parse(inputStream);

        assertThat(results.size(), is(3));
        assertThat(results.get(0), is("ITEM000001:40"));
        assertThat(results.get(1), is("ITEM000003:50"));
        assertThat(results.get(2), is("ITEM000005:60"));
    }

    @Test
    public void parse_should_have_proper_result_with_just_LFs() {

        String inputString = format("%n%n%n%n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());

        List<String> results = lexer.parse(inputStream);

        assertThat(results.size(), is(0));
    }

    @Test
    public void parse_should_have_proper_result_with_empty_string() {

        String inputString = "";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());

        List<String> results = lexer.parse(inputStream);

        assertThat(results.size(), is(0));
    }
}
