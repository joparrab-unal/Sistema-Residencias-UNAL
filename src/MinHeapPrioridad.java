inport java.util.PriorityQueue;
public class MinHeapPrioridad {
  priorityQueue<Estudiante> heap;
  public MinHEapPrioridad() {
    heap = new PriorityQueue<>(
      (a, b) -> a.puntajeSocioeconomico - b.puntajeSocioeconomico );
  }
  public void insertar(Estudiante e) {     // esto para el estudiante segun la prioridad 
    heap.add(e);
  }
public Estudiante estraerMinimo() {        // y esto para extraer el estudiante con menor pun
  public Estudiante extraerMinimo() {
    return head.poll();
  }
}
  
