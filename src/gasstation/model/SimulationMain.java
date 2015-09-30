/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.model;

/**
 *
 * @author Rodrigo Arze
 */
public class SimulationMain {

    private final Surtidor surtidor;
    private final int hoursToSimulate;

    public SimulationMain(int hoursToSimulate, int numberOfGasDispensers, int numberOfGasolineDispensers, int numberOfDieselDispensers) throws InvalidCombustibleTypeException {
        surtidor = new Surtidor(numberOfGasDispensers, numberOfGasolineDispensers, numberOfDieselDispensers);
        this.hoursToSimulate = hoursToSimulate;
    }

    public void load() throws InvalidCombustibleTypeException {
        surtidor.getGenerator().generateCars("gas");
        surtidor.getGenerator().generateCars("gasolina");
        surtidor.getGenerator().generateCars("diesel");
    }

    public void start() {
        surtidor.start();
    }

    public Surtidor getSurtidor() {
        return surtidor;
    }

}
