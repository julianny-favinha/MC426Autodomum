var audio = new Audio();
var queue = new Array();
var num;

window.onload = function start(){
	listen();
}

audio.addEventListener('ended', function(){
	next();	
});

function listen() {
	$.ajax({
		url: '',
		sucess: function(response) {
			switch(response.function) {
				case 'Play':			
					play(response.artist);
					break;
				case 'Next':
					next(response.artist);
					break;
				case 'Stop':
					stop();
					break;
			}
		}	
	});
	setTimeout(listen, 2000);
}

function getArtistID(query){
	$.ajax({
		url: 'https://api.spotify.com/v1/search',
		data: {
			q: query,
			type: 'artist'			
		},
		success: function(response) {
			var idArtist = response.artist.items[0];
			getTracksByArtist(idArtist);		
		}
	});	
}

function getTracksByArtist(id){
	$.ajax({
		url: 'https://api.spotify.com/v1/artists/'+id+'/top-tracks',
		success: function(response) {
			if (response.tracks.items.length) {				
				for (var track in response.tracks.items){
					queue.push(track);				
				}
			}		
		}
	});	
}

function play(artistName){
	getArtistID(response.artist);
	num = Math.floor((Math.random()*10));
	audio.src(queue[num]);
	audio.play();
	var rec = document.getElementById('play');
	rec.innerHTML = 'Now Playing...'
}

function next(artistName){
	if(artistName){
		getArtistID(response.artist);
		num = Math.floor((Math.random()*10));
	}
	else {
		if (num < 9){		
			num = num + 1;
		} else {
			num = 0;
		}
	}
	audio.src(queue[num]);
	audio.play();
	var rec = document.getElementById('play');
	rec.innerHTML = 'Now Playing...'
}

function stop(){
	audio.pause();
}
