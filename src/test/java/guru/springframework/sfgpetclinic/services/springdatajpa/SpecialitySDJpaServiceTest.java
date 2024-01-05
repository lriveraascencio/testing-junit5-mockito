package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

	@InjectMocks
	SpecialitySDJpaService service;

	@Mock
	SpecialtyRepository specialtyRepository;
	
	@Test
	void findByIdTest() {
		Speciality speciality = new Speciality();
		when(specialtyRepository.findById(1l)).thenReturn(Optional.of(speciality));
		Speciality foundSpecialty = service.findById(1l);
		assertThat(foundSpecialty).isNotNull();
		verify(specialtyRepository).findById(anyLong());
	}
	
	@Test
	void testDeleteByObject() {
		Speciality speciality = new Speciality();
		service.delete(speciality);
		verify(specialtyRepository).delete(any(Speciality.class));
	}

	@Test
	void deleteById() {
		service.deleteById(1l);
		service.deleteById(1l);
		verify(specialtyRepository, atLeastOnce()).deleteById(1l);
		
	}
	
	@Test
	void deleteByIdAtMost() {
		service.deleteById(1l);
		service.deleteById(1l);
		verify(specialtyRepository, atMost(5)).deleteById(1l);
		
	}
	
	@Test
	void deleteByIdNever() {
		service.deleteById(1l);
		service.deleteById(1l);
		verify(specialtyRepository, never()).deleteById(5l);
		
	}

	@Test
	void testDelete() {
		service.delete(new Speciality());
	}

}
