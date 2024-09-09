const CharacterList = () => {
  const [characters, setCharacters] = useState([]);
  const PUBLIC_KEY = "YOUR_PUBLIC_KEY";
  const HASH = "YOUR_GENERATED_HASH";

  useEffect(() => {
    axios
      .get(`https://gateway.marvel.com/v1/public/characters?ts=1&apikey=${PUBLIC_KEY}&hash=${HASH}`)
      .then((response) => {
        setCharacters(response.data.data.results);
      })
      .catch((error) => console.error(error));
  }, []);
  return (
    <div className="character-grid">
      {characters.map((character) => (
        <div key={character.id} className="character-card">
          <img src={`${character.thumbnail.path}.${character.thumbnail.extension}`} alt={character.name} />
          <h3>{character.name}</h3>
        </div>
      ))}
    </div>
  );
};
    useEffect(() => {
      if (characterId) {
        axios
          .get(`https://gateway.marvel.com/v1/public/characters/${characterId}?ts=1&apikey=${PUBLIC_KEY}&hash=${HASH}`)
          .then((response) => {
            setCharacter(response.data.data.results[0]);
          })
          .catch((error) => console.error(error));
      }
    }, [characterId]);
  
    if (!character) return <div>Select a character to view details</div>;
  
    return (
      <div className="character-detail">
        <h2>{character.name}</h2>
        <p>{character.description || "No description available"}</p>
        <h3>Comics:</h3>
        <ul>
          {character.comics.items.map((comic, index) => (
            <li key={index}>{comic.name}</li>
          ))}
        </ul>
      </div>
    );

const App = () => {
  const [selectedCharacterId, setSelectedCharacterId] = useState(null);

  const handleCharacterClick = (id) => {
    setSelectedCharacterId(id);
  };

  return (
    <div className="app">
      <CharacterList onCharacterClick={handleCharacterClick} />
      <CharacterDetail characterId={selectedCharacterId} />
    </div>
  );
};

    return (
      <div className="character-grid">
        {characters.map((character) => (
          <div
            key={character.id}
            className="character-card"
            onClick={() => onCharacterClick(character.id)}
          >
            <img src={`${character.thumbnail.path}.${character.thumbnail.extension}`} alt={character.name} />
            <h3>{character.name}</h3>
          </div>
        ))}
      </div>
    );
