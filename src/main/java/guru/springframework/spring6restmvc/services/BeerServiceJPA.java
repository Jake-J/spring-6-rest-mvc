package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.mappers.BeerMapper;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    @Override
    public List<Beer> listBeers() {
        return null;
    }

    @Override
    public Optional<Beer> getBeerById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Beer saveNewBeer(Beer beer) {
        return null;
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {

    }

    @Override
    public void deleteBeer(UUID beerId) {

    }

    @Override
    public void patchBeerById(UUID beerId, Beer beer) {

    }
}
