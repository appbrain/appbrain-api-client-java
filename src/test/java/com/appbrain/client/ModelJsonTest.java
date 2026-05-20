package com.appbrain.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.appbrain.client.model.Country;
import com.appbrain.client.model.CountryInfo;
import com.appbrain.client.model.CountryList;
import org.junit.jupiter.api.Test;

/**
 * Verifies the generated model classes and the gson + gson-fire JSON layer
 * serialize and deserialize correctly, including nested list models. Runs offline.
 */
class ModelJsonTest {

    @Test
    void buildersAndAccessorsRoundTripInMemory() {
        Country country = new Country().code("US").cpi(1.25);
        assertEquals("US", country.getCode());
        assertEquals(1.25, country.getCpi(), 0.0001);
    }

    @Test
    void equalsAndHashCodeFollowFieldValues() {
        Country a = new Country().code("US").cpi(1.25);
        Country b = new Country().code("US").cpi(1.25);
        Country different = new Country().code("DE").cpi(1.25);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, different);
    }

    @Test
    void countrySerializesAndDeserializesThroughJson() {
        Country original = new Country().code("US").cpi(1.25);

        String json = JSON.serialize(original);
        assertNotNull(json);
        assertTrue(json.contains("US"), json);

        Country parsed = JSON.deserialize(json, Country.class);
        assertEquals(original, parsed);
    }

    @Test
    void nestedListModelSerializesAndDeserializesThroughJson() {
        CountryList original = new CountryList()
                .addCountriesItem(new CountryInfo().code("US").name("United States").minCpiBid(0.5))
                .addCountriesItem(new CountryInfo().code("DE").name("Germany").minCpiBid(0.4));

        String json = JSON.serialize(original);
        assertNotNull(json);

        CountryList parsed = JSON.deserialize(json, CountryList.class);
        assertNotNull(parsed.getCountries());
        assertEquals(2, parsed.getCountries().size());
        assertEquals(original, parsed);
    }
}
