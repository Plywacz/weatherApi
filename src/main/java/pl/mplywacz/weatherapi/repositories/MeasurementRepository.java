package pl.mplywacz.weatherapi.repositories;
/*
Author: BeGieU
Date: 22.05.2019
*/

import org.springframework.data.repository.CrudRepository;
import pl.mplywacz.weatherapi.model.Measurement;

public interface MeasurementRepository extends CrudRepository<Measurement,Long> {
}
