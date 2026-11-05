
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Marvel Character Gallery</title>
    <link rel="stylesheet" href="/css/style.css">    
    </head>
<body>
    <@layout.navbar/>
    <main>
        <h2>Explore Marvel Characters</h2>
        <p>Discover your favorite Marvel heroes and villains.</p>

        <#if (message!'')?has_content>
            <p class="success-message">${message}</p>
        </#if>

        <form action="/" method="get" class="search-form">
            <label for="name">Search by name:</label>
            <input
                type="text"
                id="name"
                name="name"
                value="${searchName!''}"
                placeholder="Enter a character name"
            >

            <label for="universe">Filter by universe:</label>
            <select id="universe" name="universe">
                <option value="">All Universes</option>

                <#list universes![] as universe>
                    <option
                        value="${universe}"
                        <#if (searchUniverse!'') == universe>
                            selected
                        </#if>
                    >
                        ${universe}
                    </option>
                </#list>
            </select>

            <button type="submit">Search</button>
            <a href="/">Clear</a>
        </form>

        <section class="character-gallery">
            <#list characters![] as character>
                <article class="character-card">

                    <a href="/characters/${character.id?c}">

                        <#if (character.imageUrl!'')?has_content>
                            <img
                                src="${character.imageUrl}"
                                alt="${(character.name!'')?has_content?then(character.name, 'Marvel character')}"
                            >
                        <#else>
                            <div class="image-placeholder">
                                No image available
                            </div>
                        </#if>

                        <h3>
                            ${((character.name!'')?has_content)?then(character.name, 'Unnamed Character')}
                        </h3>
                    </a>

                    <p>
                        Alias:
                        ${((character.alias!'')?has_content)?then(character.alias, 'Unknown')}
                    </p>

                    <p>
                        Universe:
                        ${((character.universe!'')?has_content)?then(character.universe, 'Unknown')}
                    </p>

                    <a href="/characters/${character.id?c}">
                        View Details
                    </a>

                </article>
            <#else>
                <p>No characters found.</p>
            </#list>
        </section>
    </main>
</body>
</html>
