inport java.util.HashMap;
public class TablasHashEstudiantes {
  HashMap<Integer, Estudiante> estudiantes;
  public TablaHashEstudiantes() {
    estudiantes = new Hashmap<>();
  }
  public void insertar(Estudiante e){   // Para insertar el estudiante 
    estudiantes.put(e.id, e);
  }
  public Estudiante buscar(int id) {   // con esto buscamos al estudiante
    return estudiantes.get(id);
  }
  public void eliminar(int id) {  // y esto par borrarlo 
    estudiantes.remove(id) ;
  }
}
  
