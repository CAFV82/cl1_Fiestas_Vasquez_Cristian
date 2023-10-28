package domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class JpaTest {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction tx = manager.getTransaction();
		
		tx.begin();
		
		Empleado e = new Empleado();
		e.setApellido_paterno("García");
		e.setApellido_materno("Pérez");
		e.setNombres("Alan");
		e.setNro_hijos(4);
		e.setSueldo(2500.0);
		e.setFecha_nacimiento(LocalDate.now());
		
		
		// Insertar
		manager.persist(e);
		tx.commit();
		
		// Actualizar
		Empleado ap = new Empleado();
		ap.setId(1L);
		ap.setApellido_paterno("Sánchez");
		manager.merge(ap);
		
		// Eliminar
		Empleado e1 = manager.getReference(Empleado.class, 1L); 
		Empleado e2 = manager.find(Empleado.class, 2L);
		manager.remove(e2);

		tx.commit();
		
		// Listar	
		List<Empleado> lista = manager.createQuery("select * from Emp", Empleado.class).getResultList();
		for (Empleado departamento : lista) {
			System.out.println(departamento);
		}	


	}

}
