inport java.util.HashMap;
public class TablasHashEstudiantes {
  HashMap<Integer, Estudiante> estudiantes;
  public TablaHashEstudiantes() {
    estudiantes = new Hashmap<>();
  }
  public void insertar(Estudiante e){   // PAra insetar el estudiante 
    estudiantes.put(e.id, e);
  }
  public Estudiante buscar(int id) {   // con esto buscamos al estu
    retunr estudiantes.get(id);
  }
  public void eliminar(int id) {  // y esto par borrarlo 
    estudiantes.remove(id) ;
  }
}
  
