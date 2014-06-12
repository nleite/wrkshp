package sample;

public interface SampleParser<T, Sample> {

    public T parse(Sample o);
    public Sample render(T o);
}
