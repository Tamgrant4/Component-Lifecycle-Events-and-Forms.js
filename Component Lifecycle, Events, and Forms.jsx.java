npx create-react-app marvel-character-list
cd marvel-character-list
npm install axios

marvel-character-list/
├── public/
├── src/
│   ├── components/
│   │   ├── CharacterList.js
│   │   ├── CharacterDetail.js
│   ├── App.js
│   ├── index.js
├── package.json
├── .env
└── README.md

REACT_APP_MARVEL_PUBLIC_KEY=<YourPublicKey>
REACT_APP_MARVEL_HASH=<YourHash>

import React, { useState } from 'react';
import CharacterList from './components/CharacterList';
import CharacterDetail from './components/CharacterDetail';

function App() {
  const [selectedCharacter, setSelectedCharacter] = useState(null);

  const handleCharacterSelect = (character) => {
    setSelectedCharacter(character);
  };

  return (
    <div className="App">
      <h1>Marvel Characters</h1>
      <CharacterList onCharacterSelect={handleCharacterSelect} />
      {selectedCharacter && <CharacterDetail character={selectedCharacter} />}
    </div>
  );
}

export default App;

import React, { useEffect, useState } from 'react';
import axios from 'axios';

const CharacterList = ({ onCharacterSelect }) => {
  const [characters, setCharacters] = useState([]);

  useEffect(() => {
    const fetchCharacters = async () => {
      const response = await axios.get(
        `https://gateway.marvel.com/v1/public/characters?ts=1&apikey=${process.env.REACT_APP_MARVEL_PUBLIC_KEY}&hash=${process.env.REACT_APP_MARVEL_HASH}`
      );
      setCharacters(response.data.data.results);
    };
    fetchCharacters();
  }, []);

  return (
    <div className="character-list">
      {characters.map((character) => (
        <div key={character.id} onClick={() => onCharacterSelect(character)}>
          <h3>{character.name}</h3>
          <img src={`${character.thumbnail.path}.${character.thumbnail.extension}`} alt={character.name} />
        </div>
      ))}
    </div>
  );
};

export default CharacterList;

import React, { useEffect, useState } from 'react';
import axios from 'axios';

const CharacterDetail = ({ character }) => {
  const [details, setDetails] = useState(null);

  useEffect(() => {
    const fetchDetails = async () => {
      const response = await axios.get(
        `https://gateway.marvel.com/v1/public/characters/${character.id}?ts=1&apikey=${process.env.REACT_APP_MARVEL_PUBLIC_KEY}&hash=${process.env.REACT_APP_MARVEL_HASH}`
      );
      setDetails(response.data.data.results[0]);
    };
    fetchDetails();
  }, [character]);

  if (!details) return <div>Loading...</div>;

  return (
    <div className="character-detail">
      <h2>{details.name}</h2>
      <p>{details.description || 'No description available.'}</p>
      <h3>Comics:</h3>
      <ul>
        {details.comics.items.map((comic, index) => (
          <li key={index}>{comic.name}</li>
        ))}
      </ul>
    </div>
  );
};

export default CharacterDetail;
