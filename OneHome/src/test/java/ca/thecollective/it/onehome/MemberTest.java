package ca.thecollective.it.onehome;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class MemberTest {

    private Member member;

    @Before
    public void setUp() throws Exception {
        member = new Member();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getEmail() {
        assertEquals( "abc@gmail.com", member.email("abc@gmail.com"));
    }


    @Test
    public void getName() {
        assertEquals( "john", member.name("john"));
    }


    @Test
    public void getComment() {
        assertEquals("nice app",member.comment("nice app"));
    }


    @Test
    public void getNumber() {
        assertEquals(600l, 600l);
    }

    @Test
    public void getRating() {
        assertEquals("5",member.rating("5"));
    }

}