function ejemplo(lista) {
  let total = 0; 

  for (let i = 0; i < lista.length; i++) {
    console.log(lista[i]);
  }

  lista.forEach(item => {
    lista.forEach(otro => {
      console.log(item, otro);
    });
  });
}