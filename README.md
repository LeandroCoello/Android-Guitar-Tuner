# Android-Guitar-Tuner

Implementation of a guitar tuner for  Android using Tarsos library for pitch detection.


To reach the best results, five methods were chosen in order to find the characteristic frequency:

MPM (McLeod Pitch Method).
AMDF (Average Magnitude Difference Function).
YIN Pitch Tracking by AUBIO and a more performant version that uses FFT.
Dynamic Wavelet Algorithm Pitch Tracking.


References:

Tarsos DSP
https://github.com/JorenSix/TarsosDSP

McLeod Pitch Method
http://miracle.otago.ac.nz/tartini/papers/A_Smarter_Way_to_Find_Pitch.pdf

YIN
http://recherche.ircam.fr/equipes/pcm/cheveign/pss/2002_JASA_YIN.pdf

Dynamic Wavelet Algorithm Pitch Tracking
https://pdfs.semanticscholar.org/1ecf/ae4b3618f92b4267912afbc59e3a3ea1d846.pdf






