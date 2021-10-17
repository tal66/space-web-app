
let searchDestinationForm = document.querySelector("#dest-search-form");
let resultsElement = document.querySelector("#destination-results");
let queryOption;
let queryNumber;

const fetchResults = async (queryOption, queryNumber) => {
    queryNumber = queryNumber.replaceAll(',','');
    if (isNaN(parseFloat(queryNumber))){
        return null;
    }
    let fromStorage = getFromStorage(queryOption+queryNumber);
    if (fromStorage){
        return fromStorage;
    }
    let url = `${location.origin}/api/search/destination?option=${queryOption}&number=${queryNumber}`;
    let response = await fetch(url, {method: 'POST'});
    let json = await response.json().catch((e) => {return null});
    saveInStorage(queryOption+queryNumber, json);
    return json;
}

const getFromStorage = (key) => {
    let dataString = sessionStorage.getItem(key);
    return JSON.parse(dataString);
}

const saveInStorage = (key, data) => {
    let dataString = JSON.stringify(data);
    sessionStorage.setItem(key, dataString);
}

const showResults = async () => {
    let results = await fetchResults(queryOption, queryNumber);
    if (!results || results.length == 0){
        resultsElement.innerHTML = `<p>0 results.</p>`
        return;
    }
    resultsElement.innerHTML = `<h4>Results:</h4>`
    let table = document.createElement('table');
    results.forEach((planet) => {
        let template =
            `<td><a href=${location.origin}/destination/${planet.name}>${planet.name}</a></td>
            <td><span>: ${new Intl.NumberFormat('en-US').format(planet[queryOption])}</span></td>`;
        let row = table.insertRow(-1);
        row.innerHTML = template;
     })
     resultsElement.appendChild(table);
}

searchDestinationForm.addEventListener('submit', (e) => {
    e.preventDefault();
    queryOption = searchDestinationForm.querySelector('option:checked').value;
    queryNumber = searchDestinationForm.querySelector('input.search-bar').value
    showResults();
})
