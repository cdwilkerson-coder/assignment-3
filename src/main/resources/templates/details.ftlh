<#import "fragments/navbar.ftlh" as layout>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Character Details</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<@layout.navbar/>

<main>
<#if (message!'')?has_content>
    <p class="success-message">${message}</p>
</#if>
    <h2>${(character.name!'Unnamed Character')}</h2>

    <#if (character.imageUrl!'')?has_content>
        <img
            src="${character.imageUrl}"
            alt="${character.name!'Marvel Character'}"
            class="character-detail-image"
        >
    <#else>
        <p>No image available</p>
    </#if>

    <p><strong>Name:</strong>
        ${character.name!'Unknown'}
    </p>

    <p><strong>Alias:</strong>
        ${character.alias!'Unknown'}
    </p>

    <p><strong>Universe:</strong>
        ${character.universe!'Unknown'}
    </p>

    <p><strong>Description:</strong>
        ${character.description!'No description available'}
    </p>

    <a href="/">Back to Gallery</a>

    <a href="/characters/${character.id?c}/edit">
        Edit Character
    </a>

    <form
        action="/characters/${character.id?c}/delete"
        method="post"
        onsubmit="return confirm('Are you sure you want to delete this character?');"
    >
        <button type="submit">Delete Character</button>
    </form>
</main>

</body>
</html>
