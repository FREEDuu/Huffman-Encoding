import matplotlib.pyplot as plt

esempi_testi = ['2','3','4', '5']

costruzione_tab_frequenza = [ 182, 308, 467, 629]

coda_priorità = [1,2,2, 3]

albero_codifica = [ 128, 129, 133, 130]

codifica = [ 207, 382, 578, 962]

decodifica = [ 405,497, 1035, 1732]

fig, axs = plt.subplots(1, 5, figsize=(9, 3), sharey=True)
axs[0].plot(esempi_testi, costruzione_tab_frequenza)
axs[0].title.set_text('Tabella Frequenza')
axs[1].plot(esempi_testi, coda_priorità)
axs[1].title.set_text('Coda Priorità')
axs[2].plot(esempi_testi, albero_codifica)
axs[2].title.set_text('Albero Codifica')
axs[3].plot(esempi_testi, codifica)
axs[3].title.set_text('Codifica')
axs[4].plot(esempi_testi, decodifica)
axs[4].title.set_text('Decodifica')


plt.show()

