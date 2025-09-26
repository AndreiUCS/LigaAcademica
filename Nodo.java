public class Nodo<T> {

    private T elemento;
    private Nodo<T> proximo;

    public Nodo(T elemento){
        this.elemento = elemento;
        this.proximo = null;
    }

    public Nodo(T elemento, Nodo<T> proximo){
        this.elemento = elemento;
        this.proximo = proximo;
    }

    public Nodo<T> getProximo(){
        return proximo;
    }

    public void setProximo(Nodo<T> proximo){
        this.proximo = proximo;
    }

    public T getElemento(){
        return elemento;
    }

    public void setElemento(T elemento){
        this.elemento = elemento;
    }

}