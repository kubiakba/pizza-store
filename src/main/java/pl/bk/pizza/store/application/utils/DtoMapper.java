package pl.bk.pizza.store.application.utils;

import org.springframework.stereotype.Service;

import pl.bk.pizza.store.application.dto.order.NewOrderDTO;
import pl.bk.pizza.store.application.dto.order.OrderDTO;
import pl.bk.pizza.store.application.dto.order.discount.DiscountDTO;
import pl.bk.pizza.store.application.dto.order.discount.StandardDiscountDTO;
import pl.bk.pizza.store.application.dto.order.discount.TotalPriceDiscountDTO;
import pl.bk.pizza.store.application.dto.product.KebabDTO;
import pl.bk.pizza.store.application.dto.product.NewProductDTO;
import pl.bk.pizza.store.application.dto.product.PizzaToppingDTO;
import pl.bk.pizza.store.application.dto.product.ProductDTO;
import pl.bk.pizza.store.application.dto.user.AddressDTO;
import pl.bk.pizza.store.application.dto.user.NewUserDTO;
import pl.bk.pizza.store.application.dto.user.TelephoneDTO;
import pl.bk.pizza.store.application.dto.user.UserDTO;
import pl.bk.pizza.store.application.dto.product.PizzaDTO;
import pl.bk.pizza.store.application.dto.product.ProductInfoDTO;

import pl.bk.pizza.store.domain.exception.ErrorCode;
import pl.bk.pizza.store.domain.order.discount.Discount;
import pl.bk.pizza.store.domain.order.discount.price.TotalPriceDiscount;
import pl.bk.pizza.store.domain.order.discount.product.StandardDiscount;
import pl.bk.pizza.store.domain.product.PizzaTopping;
import pl.bk.pizza.store.domain.user.UserFactory;
import pl.bk.pizza.store.domain.exception.UnsupportedTypeException;
import pl.bk.pizza.store.domain.order.Order;
import pl.bk.pizza.store.domain.order.OrderFactory;
import pl.bk.pizza.store.domain.product.Kebab;
import pl.bk.pizza.store.domain.product.Pizza;
import pl.bk.pizza.store.domain.product.Product;
import pl.bk.pizza.store.domain.product.ProductFactory;
import pl.bk.pizza.store.domain.product.ProductInfo;
import pl.bk.pizza.store.domain.user.Address;
import pl.bk.pizza.store.domain.user.Telephone;
import pl.bk.pizza.store.domain.user.User;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class DtoMapper {

    private final UserFactory userFactory;
    private final ProductFactory productFactory;
    private final OrderFactory orderFactory;

    public DtoMapper(UserFactory userFactory, ProductFactory productFactory,
                     OrderFactory orderFactory){
        this.userFactory = userFactory;
        this.productFactory = productFactory;
        this.orderFactory = orderFactory;
    }

    public User mapTo(NewUserDTO newUser){
        return userFactory.createUser(newUser.getEmail(), newUser.getName(), newUser.getSurname(),
            newUser.getPassword(), mapTo(newUser.getAddress()),mapTo(newUser.getTelephone()));
    }

    public Telephone mapTo(TelephoneDTO telephone) {
        return new Telephone(telephone.getNumber());
    }

    public Address mapTo(AddressDTO address) {
        return new Address(address.getCity(),address.getStreet(), address.getStreetNumber(),address.getPostCode());
    }

    private ProductInfo mapTo(ProductInfoDTO productInfo) {
        if(productInfo instanceof KebabDTO){
            KebabDTO kebabDTO = (KebabDTO) productInfo;
            return new Kebab(kebabDTO.getDescription(), kebabDTO.getName());
        }else if (productInfo instanceof PizzaDTO){
            PizzaDTO pizzaDTO = (PizzaDTO)productInfo;
            return new Pizza(pizzaDTO.getSize(), pizzaDTO.getDough());
        }else if(productInfo instanceof PizzaToppingDTO){
            PizzaToppingDTO pizzaToppingDTO = (PizzaToppingDTO)productInfo;
            return new PizzaTopping(pizzaToppingDTO.getName());
        }
        throw new UnsupportedTypeException("Not supported " + productInfo.getClass() + " type.",
            ErrorCode.UNPROCESSABLE_PRODUCT_DTO_TYPE);
    }

    private ProductInfoDTO mapTo(ProductInfo productInfo) {
        if(productInfo instanceof Kebab){
            Kebab kebab = (Kebab) productInfo;
            final KebabDTO kebabDTO = new KebabDTO();
            kebabDTO.setDescription(kebab.getDescription());
            kebabDTO.setName(kebab.getName());
            return kebabDTO;
        }else if (productInfo instanceof Pizza){
            Pizza pizza = (Pizza)productInfo;
            final PizzaDTO pizzaDTO = new PizzaDTO();
            pizzaDTO.setDough(pizza.getDough());
            pizzaDTO.setSize(pizza.getSize());
            return pizzaDTO;
        }else if(productInfo instanceof PizzaTopping){
            PizzaTopping pizzaTopping = (PizzaTopping)productInfo;
            final PizzaToppingDTO pizzaToppingDTO = new PizzaToppingDTO();
            pizzaToppingDTO.setName(pizzaTopping.getName());
            return pizzaToppingDTO;
        }
        throw new UnsupportedTypeException("Not supported " + productInfo.getClass() + " type.",
            ErrorCode.UNPROCESSABLE_PRODUCT_TYPE);
    }

    public ProductDTO mapTo(Product product){
        final ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setStatus(product.getStatus());
        productDTO.setProductInfo(mapTo(product.getProductInfo()));
        return productDTO;
    }

    public UserDTO mapTo(User user) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setSurname(user.getSurname());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setTelephone(mapTo(user.getTelephone()));
        userDTO.setAddress(mapTo(user.getAddress()));
        userDTO.setPoints(user.getPoints());
        return userDTO;
    }

    private TelephoneDTO mapTo(Telephone telephone) {
        final TelephoneDTO telephoneDTO = new TelephoneDTO();
        telephoneDTO.setNumber(telephone.getNumber());
        return telephoneDTO;
    }

    private AddressDTO mapTo(Address address) {
        final AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setStreetNumber(address.getStreetNumber());
        addressDTO.setPostCode(address.getPostCode());
        return addressDTO;
    }

    public OrderDTO mapTo(Order order) {
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setUserEmail(order.getUserEmail());
        orderDTO.setTotalPrice(order.calculateTotalPrice());
        Set<ProductDTO> productDTOs = order.getProducts().stream().
            map(this::mapTo).
            collect(toSet());
        orderDTO.setProducts(productDTOs);
        orderDTO.setDiscounts(order.getDiscounts().stream()
            .map(this::mapTo)
            .collect(toSet()));
        return orderDTO;
    }

    private DiscountDTO mapTo(Discount discount) {
        if(discount instanceof StandardDiscount) {
            final StandardDiscountDTO standardDiscountDTO = new StandardDiscountDTO();
            standardDiscountDTO.setDiscountPercent(((StandardDiscount) discount).getDiscountPercent());
            return standardDiscountDTO;
        }
        if(discount instanceof TotalPriceDiscount) {
            final TotalPriceDiscountDTO totalPriceDiscountDTO = new TotalPriceDiscountDTO();
            totalPriceDiscountDTO.setMinAmountOfPaidMoneyToActivateDiscount(((TotalPriceDiscount) discount).getMinAmountOfPaidMoneyToActivateDiscount());
            totalPriceDiscountDTO.setMoneyToReturn(((TotalPriceDiscount) discount).getMoneyToReturn());
            return totalPriceDiscountDTO;
        }
        throw new UnsupportedTypeException("Not supported " + discount.getClass() + " type.",
            ErrorCode.UNPROCESSABLE_PRODUCT_TYPE);
    }

    public Order mapTo(NewOrderDTO orderDTO) {
        return orderFactory.createOrder(orderDTO.getEmail());
    }

    public Product mapTo(NewProductDTO product) {
        return productFactory.createProduct(product.getPrice(), mapTo(product.getProductInfo()));
    }

    public Discount mapTo(DiscountDTO discountDTO) {
        if(discountDTO instanceof TotalPriceDiscountDTO){
            return new TotalPriceDiscount(((TotalPriceDiscountDTO) discountDTO).getMinAmountOfPaidMoneyToActivateDiscount()
                ,((TotalPriceDiscountDTO) discountDTO).getMoneyToReturn());
        } else if(discountDTO instanceof StandardDiscountDTO){
                return new StandardDiscount(((StandardDiscountDTO) discountDTO).getDiscountPercent());
        }
        throw new UnsupportedTypeException("Not supported " + discountDTO.getClass() + " type.",
            ErrorCode.UNPROCESSABLE_PRODUCT_TYPE);
    }
}
