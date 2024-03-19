# android_pokemon

## Guide lines

- pokemon api : https://pokeapi.co/api/v2/
- mvvm clean architecture (hilt & room)
- view designed with jetpack compose & flow 

## Pages

- pokemon happn inspired grid list (search functionality) 
- pokemon detail

## data strategy

Single Source of Truth (SSOT) strategy to ensure Data Consistency :
- Any data updates or modifications first occur in the local database, ensuring data consistency.
- Even when there’s no internet connection, users can still access previously retrieved data from the local database, providing a seamless offline experience.

## Pokedex Road map 

P1 - setup project
P2 - data layer
P3 - detail view
P4 - ui
P5 - search use case
P6 - grid view happn
P7 - simple detail view
P8 - search ui
