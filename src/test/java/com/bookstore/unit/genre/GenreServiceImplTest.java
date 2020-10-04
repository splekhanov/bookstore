package com.bookstore.unit.genre;

import com.bookstore.exception.NotFoundException;
import com.bookstore.model.Genre;
import com.bookstore.repository.genre.GenreRepository;
import com.bookstore.service.impl.GenreServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GenreServiceImplTest {

    private static final Long id = 1L;

    @Mock
    private GenreRepository genreRepository;

    private GenreServiceImpl genreService;
    private String genreType;

    @Before
    public void setup() {
        genreService = new GenreServiceImpl(genreRepository);
        genreType = "Young adult fiction";
    }

    @Test
    public void shouldGetGenreByType() {
        Genre genre = new Genre();
        genre.setType(genreType);
        genre.setId(id);
        when(genreRepository.findGenreByType(genreType))
                .thenReturn(java.util.Optional.of(genre));
        Genre genreResult = genreService.getGenreByType(genreType);
        verify(genreRepository).findGenreByType(genreType);
        assertNotNull(genreResult);
        assertEquals(id, genreResult.getId());
        assertEquals(genreType, genreResult.getType());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowExceptionIfNoGenreFoundByType() {
        Optional<Genre> genre = Optional.empty();
        when(genreRepository.findGenreByType(genreType))
                .thenReturn(genre);
        genreService.getGenreByType(genreType);
    }
}
