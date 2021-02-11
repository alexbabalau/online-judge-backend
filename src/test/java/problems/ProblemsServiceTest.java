package problems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import application.OnlineJudgeApplication;
import model.problems.Problem;
import service.problems.ProblemsService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineJudgeApplication.class)
@Transactional
public class ProblemsServiceTest {
	
	@Autowired
	private ProblemsService problemsService;
	
	private Problem problem;
	
	
	@BeforeEach
	public void setup() {
		this.problem = new Problem(Long.valueOf(0),
				"Algoritmul lui Euclid",
				"Cel mai mare divizor comun dintre doua numere naturale a si b\r\n" + 
				"este cel mai mare numar natural pozitiv d care divide ambele numere.\r\n" + 
				"Dandu-se T perechi de numere naturale (a, b), sa se calculeze cel mai mare divizor\r\n" + 
				"comun al numerelor din fiecare pereche in parte.",
				"Fisierul de intrare euclid2.in contine pe prima linie numarul T de perechi.\r\n" + 
				"Urmatoarele T linii contin cate doua numere naturale a si b.",
				"In fisierul de iesire euclid2.out se vor scrie T linii.\r\n" + 
				"A i-a linie din acest fisier contine cel mai mare divizor comun al numerelor\r\n" + 
				"din perechea de pe linia i+1 din fisierul de intrare.",
				null,
				100,
				1,
				"",
				null,
				null,
				null,
				null);
	}
	
	@Test
	public void addProblemAndCheckExistence() {
		Problem addedProblem = this.problemsService.addProblem(problem);
		
		assertNotNull(addedProblem);
		
		Long id = addedProblem.getId();
		
		assertNotNull(id);
		
		Problem retrievedProblem = this.problemsService.getById(id);
		
		assertNotNull(retrievedProblem);
		
		assertEquals(id, retrievedProblem.getId());
		
		assertEquals(retrievedProblem.getTitle(), "Algoritmul lui Euclid");
	}
	
}
