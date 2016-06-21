var audio = new Audio();
var num;
var queue = new Array();
var queuename = new Array();
var flag = 1;



window.onload = function start(){
	listen();
}

audio.addEventListener('ended', function(){
	if (!flag){
		flag = 1;
	} else {
		next();	
	}
});


function listen(){

	if (flag){
		

		//botao controla play
		$('#playSong').on("click",function(e){
			e.preventDefault();
			audio.play();
		});

		//botao controla pause
		$('#pauseSong').on("click",function(e){
			e.preventDefault();
			audio.pause();
		});

		//botao controla next
		$('#nextSong').on("click",function(e){
			e.preventDefault();
			next(artist);
		});
	
	$.ajax({
		url: 'http://secure-bastion-88575.herokuapp.com/audio/comando',
		sucess: function(response) {
			console.log(response);
			// FALTA TRATAR RESPOSTA
		}	
	});

	}
	setTimeout(listen, 1000);
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
	$('player').attr('');
	var rec = document.getElementById('play');
	rec.innerHTML = 'Música: '+queuename[num];
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
		rec.innerHTML = 'Música: '+queuename[num];
	}
}

function stop(){
	audio.pause();
	var rec = document.getElementById('play');
	rec.innerHTML = 'Desligado...'
}