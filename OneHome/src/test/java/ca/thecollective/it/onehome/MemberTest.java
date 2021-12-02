package ca.thecollective.it.onehome;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void getName() throws Exception {
        Member utsav = new Member("Utsav");
        assertEquals("Utsav",utsav.getName());
    }

}