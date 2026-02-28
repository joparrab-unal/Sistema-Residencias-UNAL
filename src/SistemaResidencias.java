public class SistemasResidencias{
  TablaHashEstudiantes tabla; //La tabla hash permite acceder a los estudiantes usando su ID.
  MinHeapPrioridad hep; //Organiza a los estudiantes degun su puntaje socioeconómico 
  int cuposDisponibles;

  public SistemasResidencias(in cupos){
    tabla = new;
  TablaHashEstudiantes();
    heap = new MinHeapPrioridad();
    cuposDisponibles = cupos; //Asigna el numero de cupos totales disponobles en el sistema
  }
}
