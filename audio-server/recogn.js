(function () {

    function searchTracks(query) {
        $.ajax({
            url: 'https://api.spotify.com/v1/search',
            data: {
                q: query,
                type: 'track'
            },
            success: function (response) {
                if (response.tracks.items.length) {
                    var track = response.tracks.items[0];
                    audio.src = track.preview_url;
                    audio.play();
					var rec = document.getElementById('play');
					rec.innerHTML = 'Musica: '+track.name;
                }
            }
        });
    }

    function playSong(songName, artistName) {
        flag = 0;
        audio.pause();
        var query = songName;
        if (artistName) {
            query += ' artist:' + artistName;
        }

        searchTracks(query);
    }


    if (annyang) {
       
        var commands = {
                'stop': function () {
                audio.pause();
            },
                'play': function () {
                audio.play();
            },
                'play track *song': function (song) {
                playSong(song);
            },
                'play *song by *artist': function (song, artist) {
                playSong(song, artist);
            },
                'play song *song': function (song) {
                playSong(song);
            },
                'play *song': function (song) {
                playSong(song);
            }
        };

        // Add our commands to annyang
        annyang.addCommands(commands);

        // Start listening. You can call this here, or attach this call to an event, button, etc.
        annyang.start();
    }

    annyang.addCallback('error', function () {
        console.log('nada a reconhecer');
    });
})();