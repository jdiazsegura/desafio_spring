package com.meli.desafiospring.services;

import com.meli.desafiospring.dtos.PayloadDTO;
import com.meli.desafiospring.dtos.ProductDTO;
import com.meli.desafiospring.dtos.TicketDTO;
import com.meli.desafiospring.exceptions.CategoryNotFoundException;
import com.meli.desafiospring.exceptions.ProductNotFoundException;
import com.meli.desafiospring.exceptions.QuantityNotEnoughException;
import com.meli.desafiospring.repositories.ProductsRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{

    ProductsRepo productsRepo;

    public ProductServiceImpl(ProductsRepo productsRepo) {
        this.productsRepo = productsRepo;
    }

    /**
     *  Metodo principal de obtencion de productos desde el repositorio.
     *  Aca de igual forma se establece el filtro dependiendo del valor de cada una de las opciones de busqueda
     *  Para al final retornar una lista con los productos requeridos
     *
     * @param allParams Mapa con los parametros a filtrar
     * @return Lista de productos que cumplen con los parametros brindados
     * @throws ProductNotFoundException En caso de que el producto no exista en la base de datos
     * @throws CategoryNotFoundException En caso de que la categoria no exista en la base de datos
     */
    @Override
    public List<ProductDTO> getMethod(Map<String, String> allParams) throws ProductNotFoundException, CategoryNotFoundException {
        List<ProductDTO> list = new ArrayList<>();
        List<ProductDTO> result = new ArrayList<>();
        if (allParams.isEmpty()) {
            return productsRepo.findAll();
        } else {
            for (var param : allParams.keySet()) {
                var value = allParams.get(param);
                switch (param) {
                    case "category":
                        result = productsRepo.filterByCategory(value,list);
                        break;
                    case "freeShipping":
                        result = productsRepo.filterByFreeShipping(Boolean.parseBoolean(value),list);
                        break;
                    case "brand":
                        result = productsRepo.filterByBrand(value,list);
                        break;
                    case "price":
                        result = productsRepo.filterByPrice(Double.parseDouble(value),list);
                        break;
                    case "prestige":
                        result = productsRepo.filterByPrestige(Double.parseDouble(value),list);
                        break;
                    case "name":
                        result = productsRepo.filterByName(value,list);
                        break;
                    case "order":
                        switch (value) {
                            case "0":
                                return productsRepo.sortAlphAsc(result);
                            case "1":
                                return productsRepo.sortAlphDesc(result);
                            case "2":
                                return productsRepo.sortByPriceAsc(result);
                            case "3":
                                return productsRepo.sortByPriceDesc(result);
                        }
                        break;
                    default:
                        throw new ProductNotFoundException(value);
                }
            }
        }
        return result;
    }

    /**
     * Metodo para la creacion de un ticket de compra
     * @param payloadDTO Lista de compra brindada
     * @return Ticket de compra con los items definidos en la lista
     * @throws ProductNotFoundException En caso de que el producto no se encuentre
     * @throws QuantityNotEnoughException En caso de que el stock disponible del producto solicitado sea menor al requerido
     */
    @Override
    public TicketDTO createTicket(PayloadDTO payloadDTO) throws ProductNotFoundException, QuantityNotEnoughException {
        var newTicket = new TicketDTO();
        newTicket.setArticles(payloadDTO.getArticles());
        newTicket.setTotal(getTotal(productsRepo.normaliceProducts(payloadDTO.getArticles())));
        for(var element: newTicket.getArticles()){
            if (Boolean.FALSE.equals(productsRepo.stockAvailable(element))){
                throw new QuantityNotEnoughException(element.getName());
            }
        }
        return newTicket;
    }

    /**
     * Metodo para la obtencion del total de compra
     * @param list Lista de productos seleccionados en el ticket de compra
     * @return Valor de la compra
     */
    public Double getTotal(List<ProductDTO> list){
        var total = 0.0;
        for(var element : list){
            total += (element.getQuantity()*element.getPrice());
        }
        return total;
    }
}
