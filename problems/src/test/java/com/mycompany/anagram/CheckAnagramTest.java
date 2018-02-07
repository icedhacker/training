package com.mycompany.anagram;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckAnagramTest {

    private CheckAnagram checkAnagram = new CheckAnagram();

    @Test
    public void shouldReturnTrueIfAnagrams() {
        boolean actual = checkAnagram.isAnagram("abc", "bac");
        assertThat(actual).isTrue();
    }

    @Test
    public void shouldReturnTrueIfAnagramsWithRepeatedCharacters() {
        boolean actual = checkAnagram.isAnagram("aaaa", "aaaa");
        assertThat(actual).isTrue();
    }

    @Test
    public void shouldReturnFalseIfNotAnagrams() {
        boolean actual = checkAnagram.isAnagram("abc", "bbc");
        assertThat(actual).isFalse();
    }

    @Test
    public void shouldReturnFalseIfNotAnagramWithRepeatedCharacters() {
        boolean actual = checkAnagram.isAnagram("aaaa", "aaa");
        assertThat(actual).isFalse();
    }

    @Test
    public void shouldReturnTrueIfBothAreEmpty() {
        boolean actual = checkAnagram.isAnagram("", "");
        assertThat(actual).isTrue();
    }

    @Test
    public void shouldReturnFalseIfOnlyOneIsEmpty() {
        boolean actual = checkAnagram.isAnagram("", "bac");
        assertThat(actual).isFalse();
    }

    @Test
    public void shouldReturnFalseIfNullValues() {
        boolean actual = checkAnagram.isAnagram(null, "bac");
        assertThat(actual).isFalse();
    }
}
