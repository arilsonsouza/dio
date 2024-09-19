import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PokemonData } from '../models/pokemonData';

@Injectable({
  providedIn: 'root',
})
export class PokemonService {
  private baseURL: string = 'https://pokeapi.co/api/v2/pokemon/';
  private pokeData: PokemonData | any;

  constructor(private http: HttpClient) {}

  getPokemon(pokemonName: string): Observable<PokemonData> {
    this.pokeData = this.http.get<PokemonData>(`${this.baseURL}${pokemonName}`);

    return this.pokeData;
  }
}
