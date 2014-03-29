var _terms = [ '^^', '@' ]; // YOUR SEARCH TERMS GO HERE //

$(document)
		.ready(
				function() {
					/*
					 * $("#post").keydown(function() { highlightText(); });
					 */
					$('textarea.mention')
							.mentionsInput(
									{
										triggerChar : [ '@', '^^' ],
										minChars : 1,
										showAvatars : true,
										onDataRequest : function(mode, query,
												callback, triggerChar) {
											var data;
											if (triggerChar == '@') {
												data = [
														{
															id : 1,
															name : 'Kenneth Auchenberg',
															'avatar' : 'http://cdn0.4dots.com/i/customavatars/avatar7112_1.gif',
															'type' : 'contact'
														},
														{
															id : 7,
															name : 'kenneth@auchenberg.dk',
															'avatar' : 'http://cdn0.4dots.com/i/customavatars/avatar7112_1.gif',
															'type' : 'contact'
														},

														{
															id : 9,
															name : 'Kenneth Hulthin',
															'avatar' : 'http://cdn0.4dots.com/i/customavatars/avatar7112_1.gif',
															'type' : 'contact'
														} ];
											} else {
												data = [
														{
															id : 2,
															name : 'Jon Froda',
															'avatar' : 'http://2.bp.blogspot.com/-bYfBZ9na7Tg/T8mc8i7tvnI/AAAAAAAADyg/TzJ5oEP3q8w/s1600/Melly+Avatar.JPG',
															'type' : 'contact'
														},
														{
															id : 3,
															name : 'Anders Pollas',
															'avatar' : 'http://2.bp.blogspot.com/-hnv3n8kwQ5Q/T8mc84ArwhI/AAAAAAAADyo/sevQQLMHteM/s1600/Scarlett+Avatar.JPG',
															'type' : 'contact'
														},
														{
															id : 4,
															name : 'Kasper Hulthin',
															'avatar' : 'http://2.bp.blogspot.com/-bYfBZ9na7Tg/T8mc8i7tvnI/AAAAAAAADyg/TzJ5oEP3q8w/s1600/Melly+Avatar.JPG',
															'type' : 'contact'
														},
														{
															id : 5,
															name : 'Andreas Haugstrup',
															'avatar' : 'http://2.bp.blogspot.com/-hnv3n8kwQ5Q/T8mc84ArwhI/AAAAAAAADyo/sevQQLMHteM/s1600/Scarlett+Avatar.JPG',
															'type' : 'contact'
														},
														{
															id : 6,
															name : 'Pete Lacey',
															'avatar' : 'http://2.bp.blogspot.com/-bYfBZ9na7Tg/T8mc8i7tvnI/AAAAAAAADyg/TzJ5oEP3q8w/s1600/Melly+Avatar.JPG',
															'type' : 'contact'
														},
														{
															id : 8,
															name : 'Pete Awesome Lacey',
															'avatar' : 'http://2.bp.blogspot.com/-hnv3n8kwQ5Q/T8mc84ArwhI/AAAAAAAADyo/sevQQLMHteM/s1600/Scarlett+Avatar.JPG',
															'type' : 'contact'
														} ]
											}
											data = _
													.filter(
															data,
															function(item) {
																return item.name
																		.toLowerCase()
																		.indexOf(
																				query
																						.toLowerCase()) > -1
															});

											callback.call(this, data);
										}
									});

				});

function preg_quote(str) {
	return (str + '').replace(/([\\\.\+\*\?\[\^\]\$\(\)\{\}\=\!\<\>\|\:])/g,
			"\\$1");
}

function highlightText() {
	var s = $('#post').val();

	for (i = 0; i < _terms.length; i++)
		s = s.replace(new RegExp(preg_quote(_terms[i]), 'gi'),
				'<span class="highlight">' + _terms[i] + '</span>');

	myOtherTextarea.innerHTML = s.replace(new RegExp(preg_quote('\r'), 'gi'),
			'<br>');
}