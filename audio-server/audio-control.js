var audio = new Audio();
var num;
var queue = new Array();
var queuename = new Array();

window.onload = function start(){
	listen();
}

audio.addEventListener('ended', function(){
	next();	
});

function listen(){
	var artist = 'radiohead';
	search(artist);
/*	$.ajax({
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
LEITURA DE LISTA DE COMANDOS...*/ 
}

function getArtistID(query){
	$.ajax({
		url: 'https://api.spotify.com/v1/search',
		data: {
			q: query,
			type: 'artist'			
		},
		success: function(response) {
			getTracksByArtist(response.artists.items[0].id);
		}
	});	
}

function getTracksByArtist(id){
	$.ajax({
		url: 'https://api.spotify.com/v1/artists/'+id+'/top-tracks',
		data: {
			country: 'BR'
		},
		success: function(response) {
			for (var track in response.tracks){
				queue.push(response.tracks[track].preview_url);
				queuename.push(response.tracks[track].name);
			}
			play(Math.floor((Math.random()*10)));
		}
	});	
}

function search(artistName){
	getArtistID(artistName);
}

function play(num){
	audio.src = queue[num];
	audio.play();
	var rec = document.getElementById('play');
	rec.innerHTML = 'Musica: '+queuename[num];
}

function next(artistName){
	if(artistName){
		getArtistID(artistName);
	}
	else {
		if (num < 9){		
			num = num + 1;
		} else {
			num = 0;
		}
		audio.src = queue[num];
		audio.play();
		var rec = document.getElementById('play');
		rec.innerHTML = 'Musica: '+queuename[num];
	}
}

function stop(){
	audio.pause();
	var rec = document.getElementById('play');
	rec.innerHTML = 'Desligado...'
}