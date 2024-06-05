const axios = require('axios');

class TranslateService {

    async translate(string) {
        const config = {
            method: "POST",
            maxBodyLength: Infinity,
            url: "https://translate.api.cloud.yandex.net/translate/v2/translate",
            headers: {
                'Authorization': `Api-Key ${process.env.YANDEX_TRANSLATE_API_KEY}`, 
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({
                "sourceLanguageCode": "ru",
                "targetLanguageCode": "en",
                "texts": [
                  `${string}`
                ]
            })
        }

        const response = await axios.request(config);
        const translateString = response.data.translations[0].text;
        return translateString;
    }

}

module.exports = new TranslateService();