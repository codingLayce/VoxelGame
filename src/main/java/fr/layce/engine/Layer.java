package fr.layce.engine;

public interface Layer {

    public void onUpdate(float timeStep);
    public void onAttach();
    public void onDetach();

}
